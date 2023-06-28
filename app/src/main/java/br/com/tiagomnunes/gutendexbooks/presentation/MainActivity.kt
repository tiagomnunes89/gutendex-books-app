package br.com.tiagomnunes.gutendexbooks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyApp(navController)
        }
    }
}

@Composable
fun MyApp(navController: NavHostController) {
    NavHost(navController, startDestination = "bookList") {
        composable("bookList") {
            val viewModel: BookViewModel = hiltViewModel()
            BookListScreen(navController, viewModel)
        }
        composable("bookDetail/{id}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("id")
            if (bookId != null) {
                val viewModel: BookViewModel = hiltViewModel()
                BookDetailScreen(bookId.toInt(), viewModel, navController)
            }
        }
    }
}


