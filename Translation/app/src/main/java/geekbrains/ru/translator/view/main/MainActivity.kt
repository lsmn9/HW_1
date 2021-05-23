package geekbrains.ru.translator.view.main

import android.content.Intent
import android.content.res.Resources
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.provider.Settings
import android.util.Log
import android.view.*
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import geekbrains.ru.description.DescriptionActivity
import geekbrains.ru.core.BaseActivity
import geekbrains.ru.model.data.AppState
import geekbrains.ru.model.data.userdata.DataModel
import geekbrains.ru.translator.R
import geekbrains.ru.translator.di.injectDependencies
import geekbrains.ru.translator.utils.convertMeaningsToString
import geekbrains.ru.translator.view.main.adapter.MainAdapter
import geekbrains.ru.utils.ui.viewById
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope



private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
private const val HISTORY_ACTIVITY_PATH = "geekbrains.ru.history.view.history.HistoryActivity"
private const val HISTORY_ACTIVITY_FEATURE_NAME = "historyScreen"

class MainActivity : BaseActivity<AppState, MainInteractor>() {


    private val mainActivityRecyclerView by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)
    private lateinit var splitInstallManager: SplitInstallManager

    override val layoutRes = R.layout.activity_main
    override lateinit var model: MainViewModel



    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl,
                        "[${data.meanings!![0].transcription}]"
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniViewModel()
        initViews()

        val bottomPading = searchFAB.marginBottom
        ViewCompat.setOnApplyWindowInsetsListener(searchFAB){view, insets->
            view.updatePadding(bottom = bottomPading+ insets.systemWindowInsetBottom)
            insets
        }

    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        menu?.findItem(R.id.menu_screen_settings)?.isVisible =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
                val request =
                    SplitInstallRequest
                        .newBuilder()
                        .addModule(HISTORY_ACTIVITY_FEATURE_NAME)
                        .build()

                splitInstallManager
                    .startInstall(request)
                    .addOnSuccessListener {
                        val intent = Intent().setClassName(packageName, HISTORY_ACTIVITY_PATH)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext,
                            "Couldn't download feature: " + it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                true
            }
            R.id.menu_screen_settings -> {
                startActivityForResult(
                    Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY), 42
                )
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun iniViewModel() {
        check(main_activity_recyclerview.adapter == null) { "The ViewModel should be initialised first" }
        injectDependencies()
        val viewModel: MainViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {

        searchFAB.setOnClickListener(fabClickListener)
        mainActivityRecyclerView.adapter = adapter

        val bottomPadding = resources
            .getDimension(baseContext.resources
                .getIdentifier("navigation_bar_height", "dimen", "android"))
        working_frame_layout.getChildAt(1).marginBottom.div(bottomPadding.toInt())
        Log.d("Отступ=========", bottomPadding.toString())
    }

}