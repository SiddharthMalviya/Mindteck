package com.mindteck

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.mindteck.databinding.ActivityMainBinding
import com.mindteck.ui.factory.MainActivityViewModelFactory
import com.mindteck.ui.screen.adapter.LatestModelSeriesAdapter
import com.mindteck.ui.screen.adapter.ViewPagerAdapter
import com.mindteck.ui.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var adapter: ViewPagerAdapter?=null
    lateinit var mViewModel:MainActivityViewModel

    var binding : ActivityMainBinding?  =null
    var list : ArrayList<Int>? =null

    private var ivArrayDotsPager:Array<ImageView?>? =null
    private  var adapterList :LatestModelSeriesAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mViewModel = ViewModelProviders
            .of(this as FragmentActivity, MainActivityViewModelFactory(this)).get(MainActivityViewModel::class.java)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)

        mViewModel.load(0)
        pagerSetup()
        mViewModel.viewModelScope.launch {
            mViewModel.LatestListFlow.observe(this@MainActivity, Observer {
                Log.d("load==>",it.toString())

                adapterList = LatestModelSeriesAdapter(this@MainActivity,it)
                binding?.recyclerView?.adapter =adapterList
            })



        }
        binding?.edtSearch!!.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)

        binding?.edtSearch!!.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString()!=null && s.toString().length>0){
                   if (adapterList!=null){
                       adapterList!!.filter.filter(s.toString())
                   }
                }else if (s == null || s.toString() == null || s.toString().length ==0){
                    mViewModel.load(binding?.pager?.currentItem!!)
                }
            }

        })

    }


    fun pagerSetup(){
        list = ArrayList()
        list?.add(R.drawable.bmw)
        list?.add(R.drawable.odies)
        list?.add(R.drawable.bmw)



         adapter = ViewPagerAdapter(this,list!!)
        binding?.pager?.setAdapter(adapter)
        binding?.pager?.setCurrentItem(0)

        createIndicator(list!!.size)



        ivArrayDotsPager!![0]?.setImageResource(R.drawable.indicator_selected);
     binding?.pager?.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        for(i in 0..ivArrayDotsPager?.size!!-1){
            ivArrayDotsPager!![i]?.setImageResource(R.drawable.indicator_unselected);
        }
        ivArrayDotsPager!![position]?.setImageResource(R.drawable.indicator_selected);
        mViewModel.load(position)

    }

    override fun onPageScrollStateChanged(state: Int) {
    }

})

    }

    fun createIndicator(count:Int){
        ivArrayDotsPager = arrayOfNulls<ImageView?>(count)

        for (i in 0..count-1){
            ivArrayDotsPager!![i] = ImageView(this)
            val params =
                LinearLayout.LayoutParams(100, 100)
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager!![i]?.setLayoutParams(params);
            ivArrayDotsPager!![i]?.setImageResource(R.drawable.indicator_unselected);


            binding?.indicatorRoot?.addView(ivArrayDotsPager!![i])

            binding?.indicatorRoot?.bringToFront()




        }
    }


}