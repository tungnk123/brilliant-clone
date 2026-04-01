package com.example.brilliantclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brilliantclone.ui.components.*
import com.example.brilliantclone.ui.theme.*

// ─── Data ────────────────────────────────────────────────────────────────────

data class Category(
    val label: String,
    val emoji: String,
    val bgColor: Color,
    val iconColor: Color
)

data class Course(
    val title: String,
    val subtitle: String,
    val emoji: String,
    val bgColor: Color,
    val hasProgress: Boolean = false,
    val progressColor: Color = Success
)

private val categories = listOf(
    Category("Math", "🔷", MathBg, MathIcon),
    Category("CS", "💻", CSBg, CSIcon),
    Category("Data", "📊", DataBg, DataIcon),
    Category("Science", "🔬", ScienceBg, ScienceIcon),
    Category("Logic", "🧩", LogicBg, LogicIcon),
)

private val courses = listOf(
    Course("Mathematical Thinking", "Foundations", "🎨", MathBg, hasProgress = true),
    Course("Proportional Reasoning", "Numbers & Operations", "🎯", DataBg),
    Course("Negative Numbers", "Number Theory", "➕", CSBg),
    Course("Solving Equations", "Algebra", "⚗️", ScienceBg),
    Course("Geometry Fundamentals", "Shapes & Space", "📐", PurpleLight),
    Course("Probability", "Statistics", "🎲", LogicBg),
)

// ─── Bottom Nav ───────────────────────────────────────────────────────────────

private data class NavItem(val label: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector)

private val navItems = listOf(
    NavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
    NavItem("Courses", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
    NavItem("You", Icons.Filled.Person, Icons.Outlined.Person),
)

// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableIntStateOf(0) }
    var selectedNav by remember { mutableIntStateOf(1) } // Courses selected by default

    Scaffold(
        containerColor = Background,
        bottomBar = {
            BrilliantBottomNav(
                items = navItems,
                selectedIndex = selectedNav,
                onItemSelected = { selectedNav = it }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // ── Category tabs (horizontal scroll, no padding on LazyRow itself)
            item {
                Spacer(Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp)
                ) {
                    itemsIndexed(categories) { index, cat ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            TabPill(
                                label = cat.label,
                                selected = index == selectedCategory,
                                onClick = { selectedCategory = index },
                                primaryColor = Purple,
                                primaryDark = PurpleDark
                            )
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
            }

            // ── Section hero: title + description + icon
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = categories[selectedCategory].label + " Fundamentals",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            lineHeight = 30.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Master problem solving essentials",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextSecondary
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    // Hero category icon — large
                    CategoryIconBox(
                        emoji = categories[selectedCategory].emoji,
                        bgColor = categories[selectedCategory].bgColor,
                        size = 64.dp,
                        cornerRadius = 16.dp
                    )
                }
                Spacer(Modifier.height(32.dp))
            }

            // ── Course list
            itemsIndexed(courses) { index, course ->
                CourseCard(
                    course = course,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

// ─── Course Card ─────────────────────────────────────────────────────────────

@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    BrilliantCard(
        modifier = modifier.fillMaxWidth(),
        cornerRadius = 14.dp,
        onClick = {}
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CategoryIconBox(
                emoji = course.emoji,
                bgColor = course.bgColor,
                size = 48.dp,
                cornerRadius = 12.dp
            )
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = course.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = course.subtitle,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
            if (course.hasProgress) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(course.progressColor, CircleShape)
                )
            }
        }
    }
}

// ─── Bottom Nav ───────────────────────────────────────────────────────────────

@Composable
fun BrilliantBottomNav(
    items: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, NavBorder, RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp))
            .background(Color.White)
            .navigationBarsPadding()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            Box(
                modifier = Modifier
                    .size(48.dp) // touch target
                    ,
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        tint = if (isSelected) Purple else TextSecondary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = item.label,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) Purple else TextSecondary
                    )
                }
            }
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun HomeScreenPreview() {
    BrilliantTheme {
        HomeScreen()
    }
}
