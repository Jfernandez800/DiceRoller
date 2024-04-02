package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }

    @Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }

    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
        //remember result so it will keep its value.
        var result by remember { mutableStateOf( 1) }
        //variable will equal what the result is.
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = result.toString() //content description the image to the results. so it read it as a string.
            )
            //Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                //when clicked it will give a randome number between 1-6.
                result = (1..6).random()
            }) { // set the result variable to a range between 1 to 6 and then call the random() method on that range.
                Text(stringResource(R.string.roll))
            }
        }
    }
}

/*

- You might wonder why you should bother to pass a Modifier argument at all when there's a default.
The reason is because composables can undergo recomposition, which essentially means that the block
of code in the @Composable method executes again. If a Modifier object is created in a block of code,
it could potentially be recreated and that isn't efficient.

-Any time you create an Image in your app, you should provide what is called a "content description."
Content descriptions are an important part of Android development. They attach descriptions to their
respective UI components to increase accessibility.

-Composables are stateless by default, which means that they don't hold a value and can be recomposed
any time by the system, which results in the value being reset. However, Compose provides a convenient
way to avoid this. Composable functions can store an object in memory using the remember composable.

- The mutableStateOf() function returns an observable. You learn more about observables later, but
for now this basically means that when the value of the result variable changes, a recomposition is
triggered, the value of the result is reflected, and the UI refreshes.

-contentDescription is For each UI element in your app, include a description that describes the element's purpose.

 */