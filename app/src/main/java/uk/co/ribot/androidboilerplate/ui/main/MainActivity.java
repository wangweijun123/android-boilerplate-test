package uk.co.ribot.androidboilerplate.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.rank.RankActivity;

public class MainActivity extends BaseActivity implements MainMvpView, View.OnClickListener{

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
//
    @BindView(R.id.startRibort)
    Button startRibort;

    @Inject
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startRibort.setText("store servie");
        startRibort.setOnClickListener(this);
//
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mainPresenter.attachView(this);
        mainPresenter.loadContributors();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startRibort:
                startActivity(new Intent(getApplicationContext(), RankActivity.class));
                break;
            default:
                break;

        }
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
