# Pattern Map

   - **Factory Method:** No fit — there's no single product type whose
     concrete variant needs to be chosen by a subclass.
   - **Abstract Factory:** No fit — no family of related products that
     must stay mutually consistent.
   - **Builder:** GENUINE FIT — Habit has one required field (name) and
     several optional ones (reminder time, color tag, notes). Implemented
     as HabitBuilder.
   - **Prototype:** No fit — no configured "template" object gets cloned
     anywhere in this system.
   - **Singleton:** No fit at this scale — no single shared resource
     needed application-wide yet.
