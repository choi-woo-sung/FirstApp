package com.example.firstapp.fragment.ChampTier

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.firstapp.App
import com.example.firstapp.R
import com.example.firstapp.data.repository.TierRepository
import com.example.firstapp.databinding.FragmentTierBinding
import com.example.firstapp.model.ApiResponse
import com.example.firstapp.model.tier.TierLine
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author mmol93
 * @email ljws93@naver.com
 * @since 2021/11/10
 **/

@AndroidEntryPoint
class TierFragment : Fragment(R.layout.fragment_tier) {

    @Inject
    lateinit var tierRepository: TierRepository

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 챔피언 데이터를 받기 위한 객체
        // fragment 객체 생성
        val topFragment = TierTopFragment()
        val midFragment = TierMidFragment()
        val botFragment = TierBotFragment()
        val supFragment = TierSupFragment()
        val jungFragment = TierJungFragment()

        val binding = FragmentTierBinding.inflate(inflater, container, false)
        val fragmentList = arrayOf(topFragment, jungFragment, midFragment, botFragment, supFragment)

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        binding.viewPager.adapter = adapter

        // tab과 viewPager2를 연결시킨다
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab: TabLayout.Tab, i: Int ->
            if (i == 0) {
                tab.icon = requireContext().getDrawable(R.drawable.ic_top)
            } else if (i == 1) {
                tab.icon = requireContext().getDrawable(R.drawable.ic_jng)
            } else if (i == 2) {
                tab.icon = requireContext().getDrawable(R.drawable.ic_mid)
            } else if (i == 3) {
                tab.icon = requireContext().getDrawable(R.drawable.ic_adc)
            } else if (i == 4) {
                tab.icon = requireContext().getDrawable(R.drawable.ic_sup)
            }
        }.attach()

        // Jsoup에서 데이터 가져오기
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = tierRepository.execute()){
                is ApiResponse.Success ->{
                    // todo 받아온 데이터를 각각의 fragment에 뿌리기
                }
                is ApiResponse.Failure -> {
                    Toast.makeText(context, "티어 정보 로딩에 실패했습니다", Toast.LENGTH_SHORT).show()
                    Log.d("jsoup", "error: ${response.e}")
                }
            }
        }

        return binding.root
    }
}