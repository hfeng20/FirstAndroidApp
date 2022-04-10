package first.android.app

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Conversion: NavigationItem("conv", R.drawable.ic_calc, "Temperature Conversions")
    object Users: NavigationItem("users", R.drawable.ic_profile, "Users")
}
