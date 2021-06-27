package ru.geekbrains.githubclient.stubs;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ru.geekbrains.githubclient.mvp.presenter.SchedulerProvider;

public class ScheduleProviderStub implements SchedulerProvider {

    @Override
    public Scheduler ui()  {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io()  {
        return Schedulers.trampoline();
    }

}
