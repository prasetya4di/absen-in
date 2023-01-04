package id.ac.stiki.doleno.absenin.view.participant.ui.my_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.stiki.doleno.absenin.data.entity.User
import id.ac.stiki.doleno.absenin.domain.DoLogout
import id.ac.stiki.doleno.absenin.domain.GetUser
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getUser: GetUser,
    private val doLogout: DoLogout
) : ViewModel() {
    private val _myProfileState = MutableLiveData<MyProfileState>()
    var user: User? = null
    var myProfileState: LiveData<MyProfileState> = _myProfileState

    fun getUser() {
        _myProfileState.postValue(MyProfileState.LOADING)
        Executors.newSingleThreadExecutor().execute {
            user = getUser.execute()
            _myProfileState.postValue(MyProfileState.SUCCESS)
        }
    }

    fun logout() {
        Executors.newSingleThreadExecutor().execute(doLogout::execute)
    }
}