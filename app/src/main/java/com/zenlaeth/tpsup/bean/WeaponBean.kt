package com.zenlaeth.tpsup.bean

data class WeaponBean(
    var ammo: List<Ammo>,
    var assets: AssetsW,
    var attack: Attack,
    var attributes: AttributesW,
    var boostType: String,
    var coatings: List<String>,
    var crafting: CraftingW,
    var damageType: String,
    var deviation: String,
    var durability: List<Durability>,
    var elderseal: String,
    var elements: List<Element>,
    var id: Int,
    var name: String,
    var phial: Phial,
    var rarity: Int,
    var shelling: Shelling,
    var slots: List<SlotW>,
    var specialAmmo: String,
    var type: String
)

data class Ammo(
    var capacities: List<Int>,
    var type: String
)

data class AssetsW(
    var icon: Any,
    var image: String
)

data class Attack(
    var display: Int,
    var raw: Int
)

data class AttributesW(
    var affinity: String,
    var damageType: String
)

data class CraftingW(
    var branches: List<Any>,
    var craftable: Boolean,
    var craftingMaterials: List<Any>,
    var previous: Any,
    var upgradeMaterials: List<Any>
)

data class Durability(
    var blue: Int,
    var green: Int,
    var orange: Int,
    var purple: Int,
    var red: Int,
    var white: Int,
    var yellow: Int
)

data class Element(
    var damage: Int,
    var hidden: Boolean,
    var type: String
)

data class Phial(
    var damage: Any,
    var type: String
)

data class Shelling(
    var level: Int,
    var type: String
)

data class SlotW(
    var rank: Int
)