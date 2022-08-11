package viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.AppState
import model.repo.Repository
import model.repo.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun requestDataFromLocal() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(500)
            liveDataToObserve.postValue(
                AppState.Success(repository.getQuestionsFromLocal()))
        }.start()
    }

    fun restartDataFromLocal(){
        Thread {
            sleep(100)
            liveDataToObserve.postValue(
                AppState.Reload(repository.getQuestionsFromLocal()))
        }.start()
    }
}