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
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.radlance.matule.navigation.base.NavGraph
import com.radlance.matule.navigation.base.NavigationViewModel
import com.radlance.matule.navigation.base.OnboardingFirst
import com.radlance.matule.navigation.base.OnboardingSecond
import com.radlance.matule.navigation.base.OnboardingThird
import com.radlance.matule.navigation.base.SignIn
import com.radlance.matule.navigation.base.Splash
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Stack

@RunWith(AndroidJUnit4::class)
class OnBoardingTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var navigationViewModel: NavigationViewModel

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val viewModelFactory = ViewModelFactory()

            navController = TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            navigationViewModel = viewModelFactory.createNavigationViewModel()
            NavGraph(
                navController = navController,
                navigationViewModel = navigationViewModel,
                authViewModel = viewModelFactory.createAuthViewModel(),
                accountManager = FakeAccountManager(),
                sharedViewModel = viewModelFactory.createProductViewModel()
            )
        }
    }

    @After
    fun teardown() {
        navigationViewModel.setOnBoardingViewed(viewed = false)
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
    fun navHost_onBoardingSwipe_popUpOnboardingScreensStack() {
        val onBoardingScreens = listOf(
            OnboardingScreen(
                stringResId = R.string.you_have_the_power,
                imageContentDescription = R.string.onboarding_image_3,
                destination = OnboardingThird
            ),
            OnboardingScreen(
                stringResId = R.string.will_start_travel,
                imageContentDescription = R.string.onboarding_image_2,
                destination = OnboardingSecond
            ),

            OnboardingScreen(
                stringResId = R.string.welcome,
                imageContentDescription = R.string.onboarding_image_1,
                destination = OnboardingFirst
            )
        )

        val screenStack = Stack<OnboardingScreen>().apply { addAll(onBoardingScreens) }

        navController.assertCurrentDestination(Splash)
        with(composeTestRule) {
            mainClock.advanceTimeBy(1000)
            navController.assertCurrentDestination(OnboardingFirst)

            if (screenStack.isNotEmpty()) {
                swipeAndCheckNextScreen(
                    screen = screenStack.pop(),
                    isOnBoardingFirst = screenStack.size == onBoardingScreens.size - 1
                )
            } else {
                navController.assertCurrentDestination(SignIn)
            }
        }
    }

    @Test
    fun navHost_recreateAfterSwipeAllOnboarding_launchSignInScreen() {
        with(composeTestRule) {
            mainClock.advanceTimeBy(1000)
            navController.assertCurrentDestination(OnboardingFirst)

            repeat(3) {
                composeTestRule.onRoot().performTouchInput { swipeLeft() }
            }

            activityRule.scenario.recreate()
            mainClock.advanceTimeBy(1000)
            navController.assertCurrentDestination(SignIn)
        }
    }

    private fun swipeAndCheckNextScreen(
        screen: OnboardingScreen,
        isOnBoardingFirst: Boolean
    ) {
        verifyOnBoardingStackScreen(screen)
        verifyOnBoardingButtonTextState(isOnBoardingFirst)
        navController.assertCurrentDestination(screen.destination)
        composeTestRule.onRoot().performTouchInput { swipeLeft() }
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