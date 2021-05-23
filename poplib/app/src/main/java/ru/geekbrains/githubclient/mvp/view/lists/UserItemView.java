package ru.geekbrains.githubclient.mvp.view.lists;

import ru.geekbrains.githubclient.mvp.view.lists.IItemView;

public interface UserItemView extends IItemView {
    void setLogin(String text);
    void loadAvatar(String url);
}
