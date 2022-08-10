package uz.aziza.homework_9_memorygame

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val listImageOchiqYopiq = arrayOf(false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_1.setOnClickListener {
            imageClick(image_1, R.drawable.ic_itchik, 0)
        }
        image_2.setOnClickListener {
            imageClick(image_2, R.drawable.ic_animal, 1)
        }
        image_3.setOnClickListener {
            imageClick(image_3, R.drawable.ic_bori, 2)
        }
        image_4.setOnClickListener {
            imageClick(image_4, R.drawable.ic_itchik, 3)
        }
        image_5.setOnClickListener {
            imageClick(image_5, R.drawable.ic_animal, 4)
        }
        image_6.setOnClickListener {
            imageClick(image_6, R.drawable.ic_bori, 5)
        }
    }
    fun imageClick(imageView: ImageView, rasm: Int, index: Int){
        if (!animationDoing){

            if (listImageOchiqYopiq[index] == false){
                animationOchilishi(imageView, rasm, index)
            }else{
                animationYopilishi(imageView, rasm, index)
            }
        }

    }
    fun animationOchilishi(imageView: ImageView, rasm: Int, index: Int){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if(ochiqRasm == 2){
                            if (rasmId[0] == rasmId[1]){
                                imageViewAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                            }else{
                                animationYopilishi(imageViewAniqla(imageIndex[0]), -1, imageIndex[0])
                                animationYopilishi(imageViewAniqla(imageIndex[1]), -1, imageIndex[1])
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

    }




    fun animationYopilishi(imageView: ImageView, rasm: Int, index: Int?){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.ic_bulut)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        listImageOchiqYopiq[index!!] = false
        ochiqRasm--
    }

    fun imageViewAniqla(index: Int?):ImageView{
        var imageView:ImageView? = null
        when(index){
            0-> imageView = image_1
            1-> imageView = image_2
            2-> imageView = image_3
            3-> imageView = image_4
            4-> imageView = image_5
            5-> imageView = image_6

        }
        return imageView!!
    }


}