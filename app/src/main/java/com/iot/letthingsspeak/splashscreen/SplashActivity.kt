package com.iot.letthingsspeak.splashscreen

import android.animation.Animator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.animation.*
import android.util.DisplayMetrics
import android.view.animation.DecelerateInterpolator
import com.iot.letthingsspeak.MainActivity
import com.iot.letthingsspeak.R
import com.iot.letthingsspeak.R.id.splash_relative_layout
import com.iot.letthingsspeak.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    lateinit var springForce: SpringForce

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            //do stuff
            //Like your Background calls and all
            springForce = SpringForce(0f)
            splash_relative_layout.pivotX = 0f
            splash_relative_layout.pivotY = 0f
            val springAnim = SpringAnimation(splash_relative_layout, DynamicAnimation.ROTATION).apply {
                springForce.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                springForce.stiffness = SpringForce.STIFFNESS_VERY_LOW
            }
            springAnim.spring = springForce
            springAnim.setStartValue(80f)
            springAnim.addEndListener { _, _, _, _ ->
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                splash_relative_layout.animate()
                        .setListener(object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(p0: Animator?) {

                            }

                            override fun onAnimationEnd(p0: Animator?) {
                                val intent = Intent(applicationContext, LoginActivity::class.java)
                                finish()
                                startActivity(intent)
                                overridePendingTransition(0, 0)
                            }

                            override fun onAnimationCancel(p0: Animator?) {

                            }

                            override fun onAnimationStart(p0: Animator?) {

                            }

                        })
                        .setInterpolator(DecelerateInterpolator(1f))
                        .start()
            }
            springAnim.start()
        }, 1000)

    }
}
