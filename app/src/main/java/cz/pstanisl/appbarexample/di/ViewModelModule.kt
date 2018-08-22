package cz.pstanisl.appbarexample.di

import androidx.lifecycle.ViewModel
import cz.pstanisl.appbarexample.ui.detail.DetailViewModel
import cz.pstanisl.appbarexample.ui.inbox.InboxViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InboxViewModel::class)
    abstract fun bindInboxViewModel(viewModel: InboxViewModel): ViewModel

}