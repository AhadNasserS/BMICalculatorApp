package com.example.bmicalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculatorapp.ui.theme.BMICalculatorAppTheme
import com.example.bmicalculatorapp.ui.theme.Purple40
import com.example.bmicalculatorapp.ui.theme.PurpleGrey80
import com.example.bmicalculatorapp.ui.theme.purple3
import com.example.bmicalculatorapp.ui.theme.purple55

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}


@Composable
fun BMICalculator() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }
    var classification by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFCCC2DC))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Text(
            text = "BMI Calculate",
            modifier = Modifier.padding(20.dp),
            color = purple55,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = "Weight:",
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = Purple40,


        )
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Text(
            text = "Height:",
           fontSize = 20.sp,
           textAlign = TextAlign.Start,
            color = Purple40,


        )
        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Button(
            onClick = {
                val weightValue = weight.toDoubleOrNull()
                val heightValue = height.toDoubleOrNull()?.div(100) // convert height from cm to m
                if (weightValue != null && heightValue != null && weightValue > 0 && heightValue > 0) {
                    val bmi = weightValue / (heightValue * heightValue)
                 bmiResult = String.format("%.1f", bmi)
                    if (bmi < 18.5){
                        classification = "Underweight"
                    }
                    else if(18.5 <= bmi && bmi <= 24.9){
                        classification = "Normal Weight"
                    }
                    else if(25 <= bmi && bmi <= 29.9){
                        classification = "Overweight"
                    }
                    else if(30 <= bmi && bmi <= 34.9){
                        classification = "Obesity Class I"
                    }
                    else if(35 <= bmi && bmi <= 39.9){
                        classification = "Obesity Class II"
                    }
                    else if(40 <= bmi){
                        classification = "Obesity Class III"
                    }
                } else {
                    bmiResult = ""
                    classification = ""
                }
            },
            modifier = Modifier.padding(vertical = 8.dp),

        ) {
            Text("Calculate BMI"
                ,fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )


        }


        Text(
            text = "BMI Result: $bmiResult",
            fontSize = 20.sp ,
            modifier = Modifier.padding(16.dp),            //  colors = PurpleGrey80,
            fontWeight = FontWeight.Bold,
            color = purple55,



            )
        Text(
            text = "Classification: $classification",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = purple55,


        )
    }

}


