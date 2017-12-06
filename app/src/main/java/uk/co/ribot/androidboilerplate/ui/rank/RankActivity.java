package uk.co.ribot.androidboilerplate.ui.rank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.BaseModel;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

/**
 * Created by wangweijun on 2017/12/5.
 */

public class RankActivity extends BaseActivity implements RankMvpView{

    @Inject
    RankPresenter mRankPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    RankAdapter rankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_rank);

        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rankAdapter);


        mRankPresenter.attachView(this);
        mRankPresenter.getRankApps();

    }

    @Override
    public void showDataLoadSuccess(List<BaseModel> list) {
        rankAdapter.addModels(list);
        Toast.makeText(getApplicationContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataLoadError() {

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
