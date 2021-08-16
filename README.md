# PokemonApp

This is the simple app that lists several pokemons and their details.

## Technologies I used

- **Retrofit** and **OkHttp** for Http request and response handling
- **LiveData** to update ui with corresponding changes
- **Coroutine** is used to carry data to view in background
- **ViewBindng** for binding views to the corresponding pages
- **Constraint Layout** for creating layouts that is suitable with different languages and preventing overdraw issue
- **Navigation Component & SafeArgs** for navigation between pages in a safe way

## Architecture

- **MVVM** architecture is used
- **Repository Pattern** and **Use Case** is used

## How To Use
- You will see several pokemons in main page.
- If you click a pokemon, app will redirect you to detail page.
- If there is a problem with connection, there will be a warning in main page. You can fetch pokemons by refreshing page with swipe.
