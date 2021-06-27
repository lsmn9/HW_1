package ru.geekbrains.githubclient.mvp.presenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public interface SchedulerProvider {

     default Scheduler ui(){  return AndroidSchedulers.mainThread();
     }

     default Scheduler io()  {
          return Schedulers.io();
     }

}
