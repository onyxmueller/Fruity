package net.onyxmueller.android.fruity;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.onyxmueller.android.fruity.data.DatabaseManager;
import net.onyxmueller.android.fruity.data.FruitLoader;
import net.onyxmueller.android.fruity.data.FruitRecyclerAdapter;
import net.onyxmueller.android.fruity.data.SortType;
import net.onyxmueller.android.fruity.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String EXTRA_SELECTED_SORT = "selectedSort";

    private SortType selectedSortType;
    private MainActivityBinding binding;
    private FruitRecyclerAdapter fruitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            selectedSortType = extractSortType(savedInstanceState);
        }

        if (selectedSortType == null) {
            selectCommonNameOrdering();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeView();
        loadData(selectedSortType);
    }

    private void initializeView() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(this);

        setupContentView(binding);
    }

    private SortType extractSortType(Bundle bundle) {
        if (bundle.containsKey(EXTRA_SELECTED_SORT)) {
            return SortType.valueOf(bundle.getString(EXTRA_SELECTED_SORT));
        } else {
            return null;
        }
    }

    private void setupContentView(MainActivityBinding binding) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fruitAdapter = new FruitRecyclerAdapter();
        binding.recyclerView.setAdapter(fruitAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(EXTRA_SELECTED_SORT, selectedSortType.name());
        super.onSaveInstanceState(outState);
    }

    private void loadData(SortType sortType) {
        Bundle args = new Bundle();
        args.putString(EXTRA_SELECTED_SORT, sortType.name());
        getSupportLoaderManager().initLoader(sortType.hashCode(), args, fruitLoaderCallbacks);
    }

    private final LoaderManager.LoaderCallbacks<Cursor> fruitLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new FruitLoader(MainActivity.this, extractSortType(args));
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            fruitAdapter.updateCursor(cursor);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            fruitAdapter.updateCursor(null);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void selectCommonNameOrdering() {
        selectedSortType = SortType.COMMON_NAME;
    }

    private void selectScientificNameOrdering() {
        selectedSortType = SortType.SCIENTIFIC_NAME;
    }

    private void toggleSorting() {
        if (selectedSortType == SortType.COMMON_NAME) {
            selectScientificNameOrdering();
        } else {
            selectCommonNameOrdering();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                toggleSorting();
                loadData(selectedSortType);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Click events in Floating Action Button */
    @Override
    public void onClick(View v) {
        Cursor cursor = DatabaseManager.getInstance(this).queryAllFruits(SortType.NONE);
        startActivity(QuizActivity.newIntent(this, cursor));
        cursor.close();
    }
}
