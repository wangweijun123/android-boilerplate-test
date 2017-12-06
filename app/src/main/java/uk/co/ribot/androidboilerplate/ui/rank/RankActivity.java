package uk.co.ribot.androidboilerplate.ui.rank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.BaseModel;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;
import uk.co.ribot.androidboilerplate.ui.detail.DetailActivity;

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
        rankAdapter.setOnRecyclerViewItemClickListener(new RankAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v) {
                BaseModel baseModel = (BaseModel) v.getTag();
                Log.i("wang", "onItemClick baseModel:"+baseModel);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packagename", baseModel.packagename);
                startActivity(intent);
            }
        });
        rankAdapter.setOnRecyclerViewItemLongClickListener(new RankAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public void onItemLongClick(View v) {
                BaseModel baseModel = (BaseModel) v.getTag();
                Log.i("wang", "onItemLongClick baseModel:"+baseModel);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("packagename", baseModel.packagename);
                startActivity(intent);
            }
        });

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
