package com.inder.igmi.Model

data class SearchModel(
    val listed: List<Listed>,
    val message: String,
    val status: Int
)

data class Listed(
    val address: String,
    val business_name: String,
    val description: String,
    val get_time: String,
    val id: String,
    val image: String,
    val price: Any,
    val rating: String,
    val restaurant_type: List<RestaurantType>,
    val seat_available: String,
    val service_provider: String,
    val time_available: List<TimeAvailable>
)

data class RestaurantType(
    val id: String,
    val name: String
)

data class TimeAvailable(
    val time: String
)