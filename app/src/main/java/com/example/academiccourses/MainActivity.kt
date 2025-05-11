package com.example.academiccourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.academiccourses.ui.theme.AcademicCoursesTheme

data class Course(
    val title: String,
    val code: String,
    val credits: Int,
    val description: String,
    val prerequisites: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademicCoursesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(courses = sampleCourses)
                }
            }
        }
    }
}

@Composable
fun AppScreen(courses: List<Course>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.padding(32.dp))

        // Title
        Text(
            text = "Academic Courses",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF3F51B5)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CourseListScreen(
            courses = courses,
            modifier = Modifier.weight(1f)
        )

        AppFooter(
            text = "©2025 - All Rights Reserved",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE4D4D2))
        )
    }
}

// Footer Component
@Composable
fun AppFooter(text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

// Updated Courses List
private val sampleCourses = listOf(
    Course(
        title = "Data Structures and Algorithms",
        code = "CS-201",
        credits = 4,
        description = "Learn efficient data handling and problem solving.",
        prerequisites = "CS-101"
    ),
    Course(
        title = "Software Engineering",
        code = "CS-301",
        credits = 3,
        description = "Principles and practices of software development.",
        prerequisites = "CS-201"
    ),
    Course(
        title = "Web Development",
        code = "CS-202",
        credits = 5,
        description = "Full-stack web app development using modern tools.",
        prerequisites = "CS-101"
    ),
    Course(
        title = "Database Systems",
        code = "CS-401",
        credits = 4,
        description = "Efficient storage and retrieval of data.",
        prerequisites = "CS-201"
    ),
    Course(
        title = "Mobile App Development",
        code = "CS-301",
        credits = 4,
        description = "Build native mobile applications using Kotlin and Android SDK.",
        prerequisites = "CS-101, CS-102"
    ),
    Course(
        title = "Game Development",
        code = "CS-402",
        credits = 5,
        description = "Learn to create interactive games using Unity and C#.",
        prerequisites = "CS-201, CS-102"
    ),
    Course(
        title = "Artificial Intelligence",
        code = "CS-404",
        credits = 5,
        description = "Fundamentals of AI, including search algorithms, neural networks, and machine learning.",
        prerequisites = "CS-301, Math-201"
    )



)

@Composable
fun CourseListScreen(courses: List<Course>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF333333)
            )
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = course.code,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${course.credits} Credits",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (isExpanded) {
                Text(
                    text = course.description,
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Prerequisites: ${course.prerequisites}",
                    modifier = Modifier.padding(top = 6.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
