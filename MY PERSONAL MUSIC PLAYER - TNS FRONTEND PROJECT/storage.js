const Store = {
    read(key, fallback) {
        try {
            const value = localStorage.getItem(key);

            return value
                ? JSON.parse(value)
                : fallback;
        } catch {
            return fallback;
        }
    },

    write(key, value) {
        try {
            localStorage.setItem(
                key,
                JSON.stringify(value)
            );
        } catch {
            /* storage may be disabled */
        }
    },

    favorites() {
        return this.read(
            'musicPlayerFavorites',
            []
        );
    },

    toggleFavorite(id) {
        const set = this.favorites();

        const updated = set.includes(id)
            ? set.filter(x => x !== id)
            : [...set, id];

        this.write(
            'musicPlayerFavorites',
            updated
        );

        return updated;
    },

    recent() {
        return this.read(
            'musicPlayerRecentlyPlayed',
            []
        );
    },

    addRecent(id) {
        const updated = [
            id,
            ...this.recent().filter(
                x => x !== id
            )
        ].slice(0, 10);

        this.write(
            'musicPlayerRecentlyPlayed',
            updated
        );
    },

    setting(key, fallback) {
        return this.read(
            `musicPlayer${key}`,
            fallback
        );
    },

    saveSetting(key, value) {
        this.write(
            `musicPlayer${key}`,
            value
        );
    }
};