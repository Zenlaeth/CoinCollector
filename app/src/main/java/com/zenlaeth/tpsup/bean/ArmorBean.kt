package com.zenlaeth.tpsup.bean

data class ArmorBean(
    var bonus: BonusBean,
    var id: Int,
    var name: String,
    var pieces: List<PieceBean>,
    var rank: String
)

data class BonusBean(
    var id: Int,
    var name: String,
    var ranks: List<RankBean>
)

data class PieceBean(
    var armorSet: Int,
    var assets: Assets,
    var attributes: AttributesBean,
    var crafting: CraftingBean,
    var defense: DefenseBean,
    var id: Int,
    var name: String,
    var rank: String,
    var rarity: Int,
    var resistances: ResistancesBean,
    var skills: List<SkillXBean>,
    var slots: List<Any>,
    var type: String
)

data class RankBean(
    var description: String,
    var pieces: Int,
    var skill: SkillBean
)

data class SkillBean(
    var id: Int,
    var level: Int,
    var modifiers: ModifiersBean,
    var skill: Int,
    var skillName: String
)

class ModifiersBean

data class Assets(
    var imageFemale: String,
    var imageMale: String
)

class AttributesBean

data class CraftingBean(
    var materials: List<MaterialBean>
)

data class DefenseBean(
    var augmented: Int,
    var base: Int,
    var max: Int
)

data class ResistancesBean(
    var dragon: Int,
    var fire: Int,
    var ice: Int,
    var thunder: Int,
    var water: Int
)

data class SkillXBean(
    var description: String,
    var id: Int,
    var level: Int,
    var modifiers: ModifiersXBean,
    var skill: Int,
    var skillName: String
)

data class MaterialBean(
    var item: Item,
    var quantity: Int
)

data class ItemBean(
    var carryLimit: Int,
    var description: String,
    var id: Int,
    var name: String,
    var rarity: Int,
    var value: Int
)

data class ModifiersXBean(
    var damageFire: Int,
    var resistFire: Int
)