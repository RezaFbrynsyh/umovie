package com.example.tugasfe.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfe.R
import com.example.tugasfe.ui.theme.Poppins
import com.example.tugasfe.ui.theme.gray
import com.example.tugasfe.ui.theme.tosca

@Composable
fun Profile(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(Color.Black),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Profile",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = tosca,
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(26.dp)
                    .height(24.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.reza),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .height(180.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Reza Febriyansyah",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Email",
            color = Color.White,
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        InfoCard(
            text = "rezafebriyansyah21@gmail.com",
            icon = R.drawable.email
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Universitas",
            color = Color.White,
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        InfoCard(
            text = "Universitas Singaperbangsa Karawang",
            icon = R.drawable.uni
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Jurusan",
            color = Color.White,
            fontFamily = Poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        InfoCard(
            text = "Sistem Informasi",
            icon = R.drawable.major
        )
    }

}

@Composable
fun InfoCard(text: String, icon: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        color = gray
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontFamily = Poppins,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController = navController)
}
