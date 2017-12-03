package uk.co.ribot.androidboilerplate.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView{

    @Inject
    MainPresenter mainPresenter;

//    @BindView(R2.id.recycler_view)
//    RecyclerView mRecyclerView;
//
//    @BindView(R2.id.startRibort)
//    Button startRibort;

    @Inject
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        startRibort.setText("xxx");
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(adapter);

        mainPresenter.attachView(this);
        mainPresenter.loadContributors();
    }

    @Override
    public void showDataLoadSuccessTip(List<Contributor> list) {
        adapter.addContributors(list);
        Toast.makeText(getApplicationContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataLoadErrorTip() {
        Toast.makeText(getApplicationContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorUI() {

    }

    @Override
    public void hideErrorUI() {

    }
}
