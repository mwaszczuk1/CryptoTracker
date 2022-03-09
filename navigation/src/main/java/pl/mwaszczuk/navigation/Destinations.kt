package pl.mwaszczuk.navigation

enum class Destination(val route: String) {
    MainNavGraph("MAIN_NAV_GRAPH_DESTINATION"),
    Splash("SPLASH_DESTINATION"),
    DashboardNavGraph("DASHBOARD_NAV_GRAPH"),
    Dashboard("DASHBOARD_DESTINATION"),
    FiltersBottomSheet("FILTERS_BOTTOM_SHEET_DESTINATION")
}
