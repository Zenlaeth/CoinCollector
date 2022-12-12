package com.zenlaeth.tpsup.bean

import com.google.gson.annotations.SerializedName

data class MonsterBean(
    var ailments: List<Any>,
    var description: String,
    var elements: List<Any>,
    var id: Int,
    var locations: List<Location>,
    var name: String,
    var resistances: List<Resistance>,
    var rewards: List<Reward>,
    var species: String,
    var type: String,
    var weaknesses: List<Weaknesse>
)

data class Location(
    var id: Int,
    var name: String,
    var zoneCount: Int
)

data class Reward(
    var conditions: List<Condition>,
    var id: Int,
    var item: Item
)

data class Weaknesse(
    var condition: Any,
    var element: String,
    var stars: Int
)

data class Resistance(
    var condition: Any,
    var element: String,
)

data class Condition(
    var chance: Int,
    var quantity: Int,
    var rank: String,
    var subtype: String,
    var type: String
)

data class Item(
    var carryLimit: Int,
    var description: String,
    var id: Int,
    var name: String,
    var rarity: Int,
    var value: Int
)