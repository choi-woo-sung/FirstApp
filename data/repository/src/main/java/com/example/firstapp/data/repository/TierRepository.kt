package com.example.firstapp.data.repository

import com.example.firstapp.data.api.TierData

/**
 * @author mmol93
 * @email ljws93@naver.com
 * @since 2021/11/19
 **/

// tierData를 가져오게 한다
class TierRepository (private val tierData: TierData) {
    suspend fun execute() = tierData.getTierData()
}