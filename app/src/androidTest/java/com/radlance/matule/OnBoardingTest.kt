package com.radlance.matule

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.radlance.matule.data.common.DataStoreManager
import com.radlance.matule.data.onboarding.NavigationRepositoryImpl
import com.radlance.matule.navigation.base.NavGraph
import com.radlance.matule.navigation.base.NavigationViewModel
import com.radlance.matule.navigation.base.OnboardingFirst
import com.radlance.matule.navigation.base.OnboardingSecond
import com.radlance.matule.navigation.base.OnboardingThird
import com.radlance.matule.navigation.base.Splash
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Stack

class OnBoardingTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var navigationViewModel: NavigationViewModel

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val dataStoreManager = DataStoreManager(context)
            val navigationRepository = NavigationRepositoryImpl(dataStoreManager)
            navigationViewModel = NavigationViewModel(navigationRepository)
            navController = TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            NavGraph(navController = navController, navigationViewModel = navigationViewModel)
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentDestination(Splash)
    }

    @Test
    fun navHost_startDestinationWaitSecond_navigateToFirstOnboardingScreen() {
        navController.assertCurrentDestination(Splash)
        composeTestRule.mainClock.advanceTimeBy(1000)
        navController.assertCurrentDestination(OnboardingFirst)
    }

    @Test
    fun navHost_firstOnBoardingHorizontalSwipe_popUpOnBoardingScreensStack() {
        val onBoardingScreens = listOf(
            OnboardingScreen(
                stringResId = R.string.you_have_the_power,
                imageContentDescription = R.string.onboarding_image_3
            ),

            OnboardingScreen(
                stringResId = R.string.will_start_travel,
                imageContentDescription = R.string.onboarding_image_2
            ),

            OnboardingScreen(
                stringResId = R.string.welcome,
                imageContentDescription = R.string.onboarding_image_1
            )
        )

        val screenStack = Stack<OnboardingScreen>().apply { addAll(onBoardingScreens) }

        navController.assertCurrentDestination(Splash)
        with(composeTestRule) {
            mainClock.advanceTimeBy(1000)
            navController.assertCurrentDestination(OnboardingFirst)

            verifyOnBoardingStackScreen(screenStack.pop())
            verifyOnBoardingButtonTextState(isOnBoardingFirst = true)
            onRoot().performTouchInput { swipeLeft() }

            navController.assertCurrentDestination(OnboardingSecond)
            verifyOnBoardingStackScreen(screenStack.pop())
            verifyOnBoardingButtonTextState(isOnBoardingFirst = false)
            onRoot().performTouchInput { swipeLeft() }

            navController.assertCurrentDestination(OnboardingThird)
            verifyOnBoardingStackScreen(screenStack.pop())
            verifyOnBoardingButtonTextState(isOnBoardingFirst = false)
        }
    }

    private fun verifyOnBoardingStackScreen(onboardingScreen: OnboardingScreen) {
        with(composeTestRule) {
            onNodeWithStringId(onboardingScreen.stringResId).assertIsDisplayed()
            onNodeWithContentDescriptionStringId(onboardingScreen.imageContentDescription).assertIsDisplayed()
        }
    }

    private fun verifyOnBoardingButtonTextState(isOnBoardingFirst: Boolean) {
        with(composeTestRule) {
            onNodeWithStringId(R.string.start).assertDisplayedIf(isOnBoardingFirst)
            onNodeWithStringId(R.string.next).assertDisplayedIf(!isOnBoardingFirst)
        }
    }
}