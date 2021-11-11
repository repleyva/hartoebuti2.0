package repleyva.dev.hartoebuti20.model

data class OrderModel(val items: ArrayList<OrderData>)
data class OrderData(val title: String, val desc: String, val price: String, val img: Avatar)
data class Avatar(val avatar_url: String);
