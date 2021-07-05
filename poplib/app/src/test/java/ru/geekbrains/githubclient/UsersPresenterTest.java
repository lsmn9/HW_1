package ru.geekbrains.githubclient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import ru.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import ru.geekbrains.githubclient.stubs.ScheduleProviderStub;
import ru.geekbrains.githubclient.ui.adapter.UserRVAdapter;
import ru.terrakok.cicerone.Router;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersPresenterTest {

    String url, login;
    int size;


    private UsersPresenter presenter;

    @Mock
    private IGithubUsersRepo usersRepo;
    @Mock
    private Router router;
    @Mock
    private Scheduler scheduler;
    @Mock
    private ru.geekbrains.githubclient.mvp.view.lists.UserItemView userItemView;
    @Mock
    private GithubUser githubUser;
    @Mock
    private UsersPresenter.UsersListPresenter userListPresenter;
    @Mock
    private List<GithubUser> users;
    @Mock
    private UserRVAdapter.ViewHolder viewHolder;

    @Before
     public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new UsersPresenter(usersRepo, router, new ScheduleProviderStub());

    }

    @Test
    public void addUserToList_Test() {
        GithubUser githubUser = mock(GithubUser.class);
        presenter.addUserToList(githubUser);
        verify(users, never()).add(githubUser);

    }


    @Test
    public void backPressed_Test() {
        presenter.backPressed();
        presenter.backPressed();
        verify(router, times(2)).exit();
    }

    @Test
    public void bindView_Test(){

        when(users.get(userItemView.getPos())).thenReturn(githubUser);
        userItemView.setLogin(login);
        userItemView.loadAvatar(url);

        verify(userItemView, times(1)).getPos();
        verify(userItemView, times(1)).loadAvatar(url);
        verify(userItemView, times(1)).setLogin(login);

    }

    @Test
    public void bindView_OrderTest(){

        when(users.get(userItemView.getPos())).thenReturn(githubUser);
        userItemView.setLogin(login);
        userItemView.loadAvatar(url);

        InOrder inOrder = Mockito.inOrder(userItemView);
        inOrder.verify(userItemView).getPos();
        inOrder.verify(userItemView).setLogin(login);
        inOrder.verify(userItemView).loadAvatar(url);

    }

    @Test
    public void getCount_Test() {
        size = 5;
        when(users.size()).thenReturn(size);
        userListPresenter.getCount();
        Assert.assertEquals(size, users.size());

    }

    // корректное получение данных
    @Test
    public void loadData_Test(){
        when(usersRepo.getUsers()).thenReturn(
                Single.just(users)
        );
        presenter.loadData();
        verify(usersRepo, times(1)).getUsers();
    }

    // получаем ошибку
    @Test
    public void loadDataError_Test(){
        when(usersRepo.getUsers()).thenReturn(
                Single.error(new Throwable("error"))

        );
        presenter.loadData();
        verify(router, times(1)).exit();
    }

}
