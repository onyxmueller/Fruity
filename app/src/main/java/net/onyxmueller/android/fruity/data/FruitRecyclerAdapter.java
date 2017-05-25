package net.onyxmueller.android.fruity.data;

import android.database.Cursor;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.onyxmueller.android.fruity.databinding.FruitItemViewBinding;
import net.onyxmueller.android.fruity.views.FruitRowViewModel;

/**
 * RecyclerView adapter extended with project-specific required methods.
 */

public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.FruitHolder> {

    private Cursor cursor;

    public FruitRecyclerAdapter() {

    }

    public void updateCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public FruitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FruitItemViewBinding binding = FruitItemViewBinding.inflate(inflater, parent, false);
        FruitRowViewModel viewModel = new FruitRowViewModel();
        binding.setViewModel(viewModel);

        return new FruitHolder(binding.getRoot(), binding, viewModel);
    }

    @Override
    public void onBindViewHolder(FruitHolder holder, int position) {
        Fruit fruit = getItem(position);
        holder.bind(fruit);
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    /**
     * Return the {@link Fruit} represented by this item in the adapter.
     *
     * @param position Adapter item position.
     *
     * @return A new {@link Fruit} filled with this position's attributes
     *
     * @throws IllegalArgumentException if position is out of the adapter's bounds.
     */
    public Fruit getItem(int position) {
        if (position < 0 || position >= getItemCount()) {
            throw new IllegalArgumentException("Item position is out of adapter's range");
        } else if (cursor.moveToPosition(position)) {
            return new Fruit(cursor);
        }
        return null;
    }

    /* ViewHolder for each fruit item */
    public class FruitHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;
        private final FruitRowViewModel viewModel;

        public FruitHolder(View itemView, ViewDataBinding binding, FruitRowViewModel viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        public void bind(Fruit fruit) {
            viewModel.update(fruit);
            binding.executePendingBindings();
        }
    }
}
