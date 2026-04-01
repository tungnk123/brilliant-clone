package com.example.brilliantclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    val icon: ImageVector,
    val iconTint: Color,
    val iconBg: Color,
    val sectionTitle: String,
    val sectionSubtitle: String,
    val heroIcon: ImageVector
)

data class Course(
    val title: String,
    val icon: ImageVector,
    val iconTint: Color,
    val iconBg: Color,
    val hasProgress: Boolean = false
)

private val categories = listOf(
    Category(
        label = "Math",
        icon = Icons.Outlined.Calculate,
        iconTint = MathIcon,
        iconBg = MathBg,
        sectionTitle = "Foundational Math",
        sectionSubtitle = "Master problem solving essentials in math",
        heroIcon = Icons.Outlined.AutoGraph
    ),
    Category(
        label = "CS",
        icon = Icons.Outlined.Terminal,
        iconTint = CSIcon,
        iconBg = CSBg,
        sectionTitle = "Programming & CS",
        sectionSubtitle = "Speak the language of computers",
        heroIcon = Icons.Outlined.Code
    ),
    Category(
        label = "Data",
        icon = Icons.Outlined.BarChart,
        iconTint = DataIcon,
        iconBg = DataBg,
        sectionTitle = "Data Science",
        sectionSubtitle = "Turn numbers into insights",
        heroIcon = Icons.Outlined.Analytics
    ),
    Category(
        label = "Science",
        icon = Icons.Outlined.Science,
        iconTint = ScienceIcon,
        iconBg = ScienceBg,
        sectionTitle = "Scientific Thinking",
        sectionSubtitle = "Understand how the world works",
        heroIcon = Icons.Outlined.Biotech
    ),
    Category(
        label = "Logic",
        icon = Icons.Outlined.Psychology,
        iconTint = LogicIcon,
        iconBg = LogicBg,
        sectionTitle = "Logic & Puzzles",
        sectionSubtitle = "Train your reasoning skills",
        heroIcon = Icons.Outlined.Extension
    ),
)

private val coursesByCategory = mapOf(
    0 to listOf(
        Course("Mathematical Thinking", Icons.Outlined.Brush, MathIcon, MathBg, hasProgress = true),
        Course("Proportional Reasoning", Icons.Outlined.DonutSmall, DataIcon, DataBg),
        Course("Negative Numbers", Icons.Outlined.ExposureNeg1, CSIcon, CSBg),
        Course("Solving Equations", Icons.Outlined.Functions, PurpleLight, Purple),
        Course("Geometry Fundamentals", Icons.Outlined.Hexagon, ScienceIcon, ScienceBg),
        Course("Probability", Icons.Outlined.Casino, LogicIcon, LogicBg),
    ),
    1 to listOf(
        Course("Thinking in Code", Icons.Outlined.LightbulbCircle, MathIcon, MathBg, hasProgress = true),
        Course("Programming with Variables", Icons.Outlined.DataObject, CSIcon, CSBg),
        Course("Programming with Python", Icons.Outlined.Terminal, ScienceIcon, ScienceBg),
        Course("Thinking in Python", Icons.Outlined.Code, PurpleLight, Purple),
        Course("Algorithms", Icons.Outlined.AccountTree, DataIcon, DataBg),
    ),
    2 to listOf(
        Course("Data Analysis", Icons.Outlined.BarChart, DataIcon, DataBg, hasProgress = true),
        Course("Statistics", Icons.Outlined.Analytics, MathIcon, MathBg),
        Course("Machine Learning", Icons.Outlined.Memory, CSIcon, CSBg),
        Course("Data Visualization", Icons.Outlined.PieChart, LogicIcon, LogicBg),
    ),
    3 to listOf(
        Course("Physics Essentials", Icons.Outlined.Bolt, ScienceIcon, ScienceBg, hasProgress = true),
        Course("Chemistry Basics", Icons.Outlined.Science, MathIcon, MathBg),
        Course("Biology Fundamentals", Icons.Outlined.Biotech, DataIcon, DataBg),
        Course("Astronomy", Icons.Outlined.NightShelter, PurpleLight, Purple),
    ),
    4 to listOf(
        Course("Logic Puzzles", Icons.Outlined.Extension, LogicIcon, LogicBg, hasProgress = true),
        Course("Critical Thinking", Icons.Outlined.Psychology, CSIcon, CSBg),
        Course("Brain Teasers", Icons.Outlined.EmojiObjects, DataIcon, DataBg),
    ),
)

// ─── Nav ─────────────────────────────────────────────────────────────────────

private data class NavItem(val label: String, val icon: ImageVector, val activeIcon: ImageVector)

private val navItems = listOf(
    NavItem("Home", Icons.Outlined.Home, Icons.Outlined.Home),
    NavItem("Courses", Icons.Outlined.MenuBook, Icons.Outlined.MenuBook),
    NavItem("You", Icons.Outlined.Person, Icons.Outlined.Person),
)

// ─── Screen ───────────────────────────────────────────────────────────────────

@Composable
fun HomeScreen() {
    var selectedCategory by remember { mutableIntStateOf(0) }
    var selectedNav by remember { mutableIntStateOf(1) }

    val currentCategory = categories[selectedCategory]
    val currentCourses = coursesByCategory[selectedCategory] ?: emptyList()

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
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // ── Category tile row
            item {
                Spacer(Modifier.height(16.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    itemsIndexed(categories) { index, cat ->
                        CategoryTile(
                            label = cat.label,
                            icon = cat.icon,
                            iconTint = cat.iconTint,
                            iconBg = cat.iconBg,
                            selected = index == selectedCategory,
                            onClick = { selectedCategory = index }
                        )
                    }
                }
                Spacer(Modifier.height(28.dp))
            }

            // ── Section header: title + subtitle + hero icon
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = currentCategory.sectionTitle,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            lineHeight = 28.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = currentCategory.sectionSubtitle,
                            fontSize = 14.sp,
                            color = TextSecondary,
                            lineHeight = 20.sp
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    // Hero icon
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .background(currentCategory.iconBg, RoundedCornerShape(18.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = currentCategory.heroIcon,
                            contentDescription = null,
                            tint = currentCategory.iconTint,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Spacer(Modifier.height(28.dp))
            }

            // ── Course cards
            itemsIndexed(currentCourses) { _, course ->
                CourseCard(
                    course = course,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(Modifier.height(10.dp))
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
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(course.iconBg, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = course.icon,
                    contentDescription = null,
                    tint = course.iconTint,
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(Modifier.width(14.dp))
            Text(
                text = course.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
            )
            if (course.hasProgress) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Success, CircleShape)
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
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = NavBorder,
                    shape = RoundedCornerShape(0.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(58.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onItemSelected(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
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
