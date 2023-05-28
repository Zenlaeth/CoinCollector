package com.zenlaeth.tpsup.bean

data class ArmorBean(
    var armorSet: ArmorSet,
    var assets: Assets,
    var attributes: Attributes,
    var crafting: Crafting,
    var defense: Defense,
    var id: Int,
    var name: String,
    var rank: String,
    var rarity: Int,
    var resistances: Resistances,
    var skills: List<Skill>,
    var slots: List<Any>,
    var type: String
)

data class ArmorSet(
    var bonus: Any,
    var id: Int,
    var name: String,
    var pieces: List<Int>,
    var rank: String
)

data class Assets(
    var imageFemale: String,
    var imageMale: String
)

data class Attributes(
    var requiredGender: String
)

data class Crafting(
    var materials: List<Material>
)

data class Defense(
    var augmented: Int,
    var base: Int,
    var max: Int
)

data class Resistances(
    var dragon: Int,
    var fire: Int,
    var ice: Int,
    var thunder: Int,
    var water: Int
)

data class Skill(
    var description: String,
    var id: Int,
    var level: Int,
    var modifiers: Modifiers,
    var skill: Int,
    var skillName: String
)

data class Material(
    var item: Item,
    var quantity: Int
)

data class ItemA(
    var carryLimit: Int,
    var description: String,
    var id: Int,
    var name: String,
    var rarity: Int,
    var value: Int
)

class Modifiers