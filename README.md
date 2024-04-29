<p align="center">
 <h1 align="center">Final Reality</h1>
 <p align="center">Simplified clone of the renowned game, Final Fantasy!</p>
</p>

### Final Reality

Final Reality is a simplified clone of the renowned game, Final Fantasy. Its main purpose is to
serve as an educational tool, teaching foundational programming concepts.

# Characteristics <!-- omit in toc -->

- [Classes and constructors](#classes-and-constructors)
    - [Entities](#entities)
    - [Characters](#characters)
    - [Weapons](#weapons)
    - [Enemies](#enemies)
    - [Teams](#teams)
    - [Turn Scheduler](#turn-scheduler)

# Classes and constructors

Constructors within the project were made in such a way that certain specific elements are not required and can be later given

## Entities

There's two types of entities under the IEntity interface, [characters](#characters) and [enemies](#enemies), both sharing some characteristics,
those being:

- A name.
- An amount of life points (HP).
- A defense stat.
- A weight value.

Out of these stats, they are required to have:

- HP greater than or equal to 0.
- Defense value greater than or equal to 0.
- Weight value greater than 0.

## Characters

Characters all share the common root interface ICharacter that extends IEntity, which grants all sub instances have:

- A name.
- An amount of life points (HP).
- A defense stat.
- A weight value.
- Ability to equip weapons or other characters.

Additionally, there's magical characters who have an additional stat:

- Mana (mana points), which controls their ability to use magic and is required to be greater than or equal to 0.

At last, there's traits that define which characters can use specific [weapons](#weapons), although quite self-explanatory, as follows:

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

Weapons are a crucial part of the game as they can be equipped and used in combat. Their functionality is to give the [characters](#characters) the capacity to damage their opponents.
Weapons are instances of the interface IWeapon which grants all sub instances have:

- A name.
- An attack value.
- A weight value.
- An optional owner.

Out of these stats, they are required to have:

- Attack greater than 0.
- Weight value greater than or equal to 0.

Additionally, there's magical weapons who have an additional stat:

- Magic Attack, which controls their ability to use spells and is required to be greater than 0.

The weapons are:

- Common Weapons:
  - Sword
  - Axe
  - Bow
- Magical Weapons:
  - Wand
  - Staff

A weapon can be equipped by up to one character at a time. It can be exchanged during said characters turn for 
some weapon without owner.
> The change of weapon mechanic is under revision, for it may lead to unexpected outcomes in turn taking.

## Enemies

Enemies are character-like instances, they are what characters have to fight against in order to win a game.
Enemies are instances of the class Enemy which grants all sub instances have:

- A name.
- An amount of life points (HP).
- An attack value.
- A defense stat.
- A weight value.

Out of these stats, they are required to have:

- HP greater than or equal to 0.
- Defense greater than or equal to 0.
- Attack greater than 0.
- Weight value greater than 0.

## Teams

Both characters and Enemies are organized in teams. Each team has a specified capacity for members.
The teams are represented as ArrayBuffer instances with their maximum and minimum lengths decided upon creation. The teams are:

- Party (Exactly 3 ICharacter instances)
- Enemies (Up to 5 IEnemy instances, at least 1)

### Adding/Removing Members from Teams

When a new member tries to join a full team, it will just not be allowed and nothing will happen.
Changing a member of a team is always possible.
Removing a character is achieved with the change method, passing Nothing as the newMember parameter.
If removing the character would leave the team with less than minimum members, nothing happens.

### Knowledge of defeat

A team is required to know if it is defeated or not, the way it is implemented here is by checking if there is any alive member left on the team. 
Alive is defined as having HP greater than 0.

## Turn Scheduler

Throughout the gameplay, a main feature to expect is the assignation of turns, for which the implementation goes as follows.

- There's characters and enemies that need to take part into fighting.
- All entities need an action bar to take turns, reset upon taking turn.
- The limit for the action bar has to be calculated.
- The turn scheduler needs to be able to add and remove entities.
- All action bars should be raised when no entity can do anything.
- It should be known if an entity can do something.
- Entities ready to move should do so in order, depending on surplus (desc).
- Only one entity at a time can be taking action, it should be known which.

> Requirements not fully described, so all methods remain public for now.

### Turn Taking

All entities have an action bar, and it's their time to move when it is full.
The action bar is defined as a limit, upon reaching it, the turn will be queued.
To decide the turn when many fulfill the requirements, the surplus is calculated, greater surplus takes first turn.
Upon taking a turn, the action bar resets.
When all entities are done with the turns for the round, all action bars are raised by a constant amount each time.

### Storage

Entities are stored in an ArrayBuffer, same for their action bars, which are respective in terms of index.

Adding an entity will append it to the last element of the characters ArrayBuffer, creating a new action bar starting
at zero appended to the last element of the action bars ArrayBuffer.

Removing a character works by finding the index and removing the elements there from characters array and bars array.

## License

This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).