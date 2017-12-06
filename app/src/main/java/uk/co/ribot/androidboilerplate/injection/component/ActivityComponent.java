package uk.co.ribot.androidboilerplate.injection.component;

import dagger.Component;
import uk.co.ribot.androidboilerplate.injection.PerActivity;
import uk.co.ribot.androidboilerplate.injection.module.ActivityModule;
import uk.co.ribot.androidboilerplate.ui.detail.DetailActivity;
import uk.co.ribot.androidboilerplate.ui.main.MainActivity;
import uk.co.ribot.androidboilerplate.ui.rank.RankActivity;

/**
 * This component inject dependencies to all Activities across the application
 *
 * ActivityComponent  --继承--> ConfigPersistentComponent --继承--> ApplicationComponent
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(RankActivity rankActivity);

    void inject(DetailActivity detailActivity);
}
