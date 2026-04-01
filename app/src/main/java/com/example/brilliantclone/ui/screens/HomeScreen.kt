package com.example.brilliantclone.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Brush
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brilliantclone.ui.components.*
import com.example.brilliantclone.ui.theme.*
import com.example.brilliantclone.ui.theme.PlusJakartaSansFontFamily

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
        Course("Mathematical Thinking", Icons.Outlined.Calculate, MathIcon, MathBg, hasProgress = true),
        Course("Proportional Reasoning", Icons.Outlined.DonutSmall, DataIcon, DataBg),
        Course("Negative Numbers", Icons.Outlined.ExposureNeg1, CSIcon, CSBg),
        Course("Solving Equations", Icons.Outlined.Functions, MathIcon, MathBg),
        Course("Geometry Fundamentals", Icons.Outlined.Hexagon, ScienceIcon, ScienceBg),
        Course("Probability", Icons.Outlined.Casino, LogicIcon, LogicBg),
    ),
    1 to listOf(
        Course("Thinking in Code", Icons.Outlined.LightbulbCircle, MathIcon, MathBg, hasProgress = true),
        Course("Programming with Variables", Icons.Outlined.DataObject, CSIcon, CSBg),
        Course("Programming with Python", Icons.Outlined.Terminal, ScienceIcon, ScienceBg),
        Course("Thinking in Python", Icons.Outlined.Code, MathIcon, MathBg),
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
        Course("Astronomy", Icons.Outlined.Nightlight, CSIcon, CSBg),
    ),
    4 to listOf(
        Course("Logic Puzzles", Icons.Outlined.Extension, LogicIcon, LogicBg, hasProgress = true),
        Course("Critical Thinking", Icons.Outlined.Psychology, CSIcon, CSBg),
        Course("Brain Teasers", Icons.Outlined.EmojiObjects, DataIcon, DataBg),
    ),
)

// ─── Nav ─────────────────────────────────────────────────────────────────────

data class NavItem(val label: String, val icon: ImageVector, val activeIcon: ImageVector)

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

            // ── Section header with lavender gradient band (#3 fix)
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFEEEBFF),
                                    Color(0xFFF4F2FF),
                                    Color(0xFFFAF9FF),
                                    Color.White
                                )
                            )
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(top = 20.dp, bottom = 28.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentCategory.sectionTitle,
                                fontFamily = PlusJakartaSansFontFamily,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                lineHeight = 28.sp
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = currentCategory.sectionSubtitle,
                                fontFamily = PlusJakartaSansFontFamily,
                                fontSize = 13.sp,
                                color = TextSecondary,
                                lineHeight = 19.sp
                            )
                        }
                        Spacer(Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(currentCategory.iconBg, RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = currentCategory.heroIcon,
                                contentDescription = null,
                                tint = currentCategory.iconTint,
                                modifier = Modifier.size(46.dp)
                            )
                        }
                    }
                }
            }

            // ── Course cards with connector lines (#2 fix)
            itemsIndexed(currentCourses) { index, course ->
                CourseCard(
                    course = course,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                // Connector line between cards (not after last)
                if (index < currentCourses.lastIndex) {
                    Box(
                        modifier = Modifier
                            .padding(start = 48.dp)
                            .width(2.dp)
                            .height(8.dp)
                            .background(Color(0xFFE0DDD8))
                    )
                }
            }

            // ── Colored feature sections
            item { Spacer(Modifier.height(28.dp)) }
            item {
                ColoredSectionCard(
                    title = "Daily Challenge",
                    subtitle = "Streak: 7 days — keep it going",
                    icon = Icons.Outlined.LocalFireDepartment,
                    startColor = Color(0xFF7C4DFF),
                    endColor = Color(0xFF9C6FFF),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            item { Spacer(Modifier.height(12.dp)) }
            item {
                ColoredSectionCard(
                    title = "New for You",
                    subtitle = "Curated picks based on your progress",
                    icon = Icons.Outlined.AutoAwesome,
                    startColor = Color(0xFF1A8FE3),
                    endColor = Color(0xFF42B4FF),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            item { Spacer(Modifier.height(12.dp)) }
            item {
                ColoredSectionCard(
                    title = "Continue Learning",
                    subtitle = "Pick up where you left off",
                    icon = Icons.Outlined.PlayCircle,
                    startColor = Color(0xFFE87722),
                    endColor = Color(0xFFFF9600),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }
    }
}

// ─── Course Card ─────────────────────────────────────────────────────────────

@Composable
fun CourseCard(course: Course, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressOffset = 5.dp
    val currentOffset by animateDpAsState(
        targetValue = if (isPressed) pressOffset else 0.dp,
        animationSpec = spring(dampingRatio = 0.55f, stiffness = 600f),
        label = "card_press"
    )

    // Outer box reserves space for the shadow layer below
    Box(modifier = modifier.fillMaxWidth().padding(bottom = pressOffset)) {
        // Shadow layer — same color as stroke for clean 3D look
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 88.dp)
                .offset(y = pressOffset)
                .background(Border, RoundedCornerShape(16.dp))
        )
        // Face layer — slides down on press
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 88.dp)
                .offset(y = currentOffset)
                .border(1.5.dp, Border, RoundedCornerShape(16.dp))
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable(interactionSource = interactionSource, indication = null) {}
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(course.iconBg.copy(alpha = 0.22f), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = course.icon,
                        contentDescription = null,
                        tint = course.iconTint,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(Modifier.width(14.dp))
                Text(
                    text = course.title,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    modifier = Modifier.weight(1f)
                )
                if (course.hasProgress) {
                    Spacer(Modifier.width(8.dp))
                    Box(modifier = Modifier.size(8.dp).background(Success, CircleShape))
                }
            }
        }
    }
}

// ─── Colored Section Card ─────────────────────────────────────────────────────

@Composable
fun ColoredSectionCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    startColor: Color,
    endColor: Color,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressOffset = 5.dp
    val currentOffset by animateDpAsState(
        targetValue = if (isPressed) pressOffset else 0.dp,
        animationSpec = spring(dampingRatio = 0.55f, stiffness = 600f),
        label = "section_press"
    )

    Box(modifier = modifier.fillMaxWidth().padding(bottom = pressOffset)) {
        // Shadow layer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .offset(y = pressOffset)
                .clip(RoundedCornerShape(16.dp))
                .background(startColor.copy(alpha = 0.5f))
        )
        // Face layer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .offset(y = currentOffset)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.linearGradient(listOf(startColor, endColor)))
                .clickable(interactionSource = interactionSource, indication = null) {}
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(title, fontFamily = PlusJakartaSansFontFamily, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Spacer(Modifier.height(2.dp))
                    Text(subtitle, fontFamily = PlusJakartaSansFontFamily, fontSize = 12.sp, color = Color.White.copy(alpha = 0.82f))
                }
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
        shadowElevation = 8.dp
    ) {
        Column {
            HorizontalDivider(thickness = 1.dp, color = NavBorder)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex
                    Box(
                        modifier = Modifier
                            .width(72.dp)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onItemSelected(index) },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        if (isSelected) Purple.copy(alpha = 0.1f) else Color.Transparent,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = if (isSelected) Purple else TextSecondary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = item.label,
                                fontFamily = PlusJakartaSansFontFamily,
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
