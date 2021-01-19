package com.yeuristic.dynamicfeature

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class DynamicActivityViewModel: BaseObservable() {

    var isLogin: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }
}