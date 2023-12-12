package com.mrbaikal.composeanimationdemo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mrbaikal.composeanimationdemo.screen.demo1.Demo1
import com.mrbaikal.composeanimationdemo.screen.demo10.Demo10
import com.mrbaikal.composeanimationdemo.screen.demo2.Demo2
import com.mrbaikal.composeanimationdemo.screen.demo3.Demo3
import com.mrbaikal.composeanimationdemo.screen.demo4.Demo4
import com.mrbaikal.composeanimationdemo.screen.demo5.Demo5
import com.mrbaikal.composeanimationdemo.screen.demo6.Demo6
import com.mrbaikal.composeanimationdemo.screen.demo7.Demo7
import com.mrbaikal.composeanimationdemo.screen.demo8.Demo8
import com.mrbaikal.composeanimationdemo.screen.demo9.Demo9
import com.mrbaikal.composeanimationdemo.screen.demoList.DemoList

fun NavGraphBuilder.MainNavigation(navController: NavHostController) {
    composable(route = "demoList") {
        DemoList(navController)
    }
    composable("demo1") {
        Demo1()
    }
    composable("demo2") {
        Demo2()
    }
    composable("demo3") {
        Demo3()
    }
    composable("demo4") {
        Demo4()
    }
    composable("demo5") {
        Demo5()
    }
    composable("demo6") {
        Demo6()
    }
    composable("demo7") {
        Demo7()
    }
    composable("demo8") {
        Demo8()
    }
    composable("demo9") {
        Demo9()
    }
    composable("demo10") {
        Demo10()
    }
}