package ru.geekbrains.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.presenter.list.IUserOwnListPresenter;
import ru.geekbrains.githubclient.mvp.view.UserOwnItemView;

public class UserOwnAdapter extends RecyclerView.Adapter<UserOwnAdapter.ViewHolder> {

    IUserOwnListPresenter ownPresenter;
    public UserOwnAdapter(IUserOwnListPresenter presenter) {
        this.ownPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user_repo, parent, false);

        UserOwnAdapter.ViewHolder viewHolder = new UserOwnAdapter.ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ownPresenter.onItemClick(holder);
            }
        });
        ownPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return ownPresenter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements UserOwnItemView {
        TextView textView;
        int position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.tv_repo);
        }

        @Override
        public void setName(String name) {
            textView.setText(name);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
