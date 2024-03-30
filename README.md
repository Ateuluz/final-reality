<p align="center">
 <h1 align="center">Final Reality</h1>
 <p align="center">Simplified clone of the renowned game, Final Fantasy!</p>
</p>

### Final Reality

Final Reality is a simplified clone of the renowned game, Final Fantasy. Its main purpose is to
serve as an educational tool, teaching foundational programming concepts.

# Characteristics <!-- omit in toc -->

- [Classes and constructors](#classes-and-constructors)
    - [Characters](#characters)
    - [Weapons](#weapons)

# Classes and constructors

Constructors within the project were made in such a way that certain specific elements are not required and can be later given

## Characters

Characters all share the common root trait Character which garants all subinstances have:

- A name.
- An amount of life points (HP).
- A defense stat.
- A weight value.
- Ability to equip weapons or other characters.

Out of this stats, they are required to have:

- HP greater than or equal to 0.
- Defense value greater than or equal to 0.
- Weight value greater than 0.

Aditionally, there's magical characters who have an aditional stat:

- Mana (mana points), which controls their ability to use magic and is required to be greater than or equal to 0.

At last, there's traits that define what character can use what [weapons](#weapons) they can use being quite self explainatory, which are as follows:

- SwordBearer
- AxeBearer
- BowBearer
- WandUser
- StaffUser

The characters and their respective weapons are:

- Common Characters:
  - Paladin (SwordBearer, AxeBearer)
  - Warrior (SwordBearer, AxeBearer, BowBearer)
  - Ninja   (SwordBearer, BowBearer, WandUser)
- Magical Characters:
  - White Mage (WandUser, StaffUser, BowBearer)
  - Black Mage (WandUser, StaffUser, SwordBearer)

## Weapons

Weapons are a crusial part of the game as they can be equipped and used in combat. Their functionality is to give the [characters](#characters) the capacity to damage their opponents.
Weapons are instances of the trait Weapon which garants all subinstances have:

- A name.
- An attack value.
- A weight value.
- An owner.

Out of this stats, they are required to have:

- Attack greater than or equal to 0.
- Weight value greater than or equal to 0.

Aditionally, there's magical weapons who have an aditional stat:

- Magic Attack, which controls their ability to use spells and is required to be greater than or equal to 0.

The weapons are:

- Common Weapons:
  - Sword
  - Axe
  - Bow
- Magical Weapons:
  - Wand
  - Staff

## License

This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).