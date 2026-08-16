const $ = selector => document.querySelector(selector);

const player = new MusicPlayer(updateUI);
let currentView = 'home';

const formatTime = seconds =>
    Number.isFinite(seconds)
        ? `${Math.floor(seconds / 60)}:${String(Math.floor(seconds % 60)).padStart(2, '0')}`
        : '0:00';

// Keep the interface intact if a custom cover path is unavailable.
document.addEventListener(
    'error',
    event => {
        if (
            event.target.matches('img') &&
            !event.target.src.endsWith('cover-1.svg')
        ) {
            event.target.src = 'assets/images/cover-1.svg';
        }
    },
    true
);

function favorite(id) {
    return Store.favorites().includes(id);
}

function trackMarkup(song, compact = false) {
    return `
        <article
            class="track ${song.id === player.song.id ? 'active' : ''}"
            data-id="${song.id}"
        >
            <button
                class="track-play"
                aria-label="Play ${song.title}"
            >
                ${song.id === player.song.id && !player.audio.paused ? 'Ⅱ' : '▶'}
            </button>

            <img src="${song.cover}" alt="">

            <div class="track-info">
                <strong>${song.title}</strong>
                <span>${song.artist}</span>
            </div>

            ${
                compact
                    ? ''
                    : `<span class="album-name">${song.album}</span>`
            }

            <button
                class="heart-button ${favorite(song.id) ? 'is-favorite' : ''}"
                data-favorite="${song.id}"
                aria-label="Toggle favorite for ${song.title}"
            >
                ${favorite(song.id) ? '♥' : '♡'}
            </button>
        </article>
    `;
}

function renderTracks(query = '') {
    const value = query.trim().toLowerCase();

    const results = songs.filter(song =>
        [song.title, song.artist, song.album].some(value =>
            value.toLowerCase().includes(query)
        )
    );

    $('#trackList').innerHTML = results.length
        ? results.map(song => trackMarkup(song)).join('')
        : `<p class="empty">No songs found. Try another search.</p>`;

    $('#songCount').textContent =
        `${results.length} ${results.length === 1 ? 'song' : 'songs'}`;
}

function renderCollections() {
    const favorites = songs.filter(song => favorite(song.id));

    const recent = Store.recent()
        .map(id => songs.find(song => song.id === id))
        .filter(Boolean);

    $('#favoritesList').innerHTML = favorites.length
        ? favorites.map(song => trackMarkup(song, true)).join('')
        : `
            <div class="empty-state">
                <span aria-hidden="true">♡</span>
                <strong>No favorites yet</strong>
                <p>Save songs you love and they’ll appear here.</p>
            </div>
        `;

    $('#recentList').innerHTML = recent.length
        ? recent.map(song => trackMarkup(song, true)).join('')
        : `
            <div class="empty-state">
                <span aria-hidden="true">◷</span>
                <strong>Nothing played yet</strong>
                <p>Your recently played songs will appear here.</p>
            </div>
        `;

    $('#favoriteCount').textContent = favorites.length || '';
}

function renderAll() {
    renderTracks($('#searchInput').value);
    renderCollections();
}

function updateUI(reason) {
    const song = player.song;
    const playing = !player.audio.paused;

    $('#nowTitle').textContent = $('#playerTitle').textContent = song.title;
    $('#nowArtist').textContent = $('#playerArtist').textContent = song.artist;

    ['nowCover', 'playerCover'].forEach(id => {
        const el = $('#' + id);
        el.src = song.cover;
        el.alt = `Cover art for ${song.title}`;
    });

    $('#nowCover').classList.remove('art-pop');
    void $('#nowCover').offsetWidth;
    $('#nowCover').classList.add('art-pop');

    $('#playBtn').textContent = playing ? 'Ⅱ' : '▶';
    $('#playBtn').setAttribute(
        'aria-label',
        playing ? 'Pause' : 'Play'
    );

    $('#heroPlay').innerHTML = playing
        ? '<span>Ⅱ</span> Pause collection'
        : '<span>▶</span> Play collection';

    const isFav = favorite(song.id);

    ['nowFavorite', 'playerFavorite'].forEach(id => {
        const el = $('#' + id);

        el.textContent = isFav ? '♥' : '♡';
        el.classList.toggle('is-favorite', isFav);

        el.setAttribute(
            'aria-label',
            `${isFav ? 'Remove' : 'Add'} ${song.title} ${
                isFav ? 'from' : 'to'
            } favorites`
        );
    });

    $('#currentTime').textContent = formatTime(player.audio.currentTime);
    $('#duration').textContent = formatTime(player.audio.duration);

    $('#progressBar').value = player.audio.duration
        ? (player.audio.currentTime / player.audio.duration) * 100
        : 0;

    $('#shuffleBtn').classList.toggle(
        'selected',
        player.shuffle
    );

    $('#repeatBtn').classList.toggle(
        'selected',
        player.repeat !== 'off'
    );

    $('#repeatBtn').textContent =
        player.repeat === 'one' ? '↻¹' : '↻';

    $('#repeatBtn').setAttribute(
        'aria-label',
        `Repeat ${player.repeat}`
    );

    if (['track', 'play', 'pause', 'error'].includes(reason)) {
        renderAll();
    }

    if (reason === 'error') {
        toast(
            'Unable to play this song. Check that the file exists in assets/audio.'
        );
    }
}

function playId(id) {
    player.load(Number(id));
}

function toggleFavorite(id) {
    Store.toggleFavorite(Number(id));
    renderAll();
    updateUI();
}

function toast(message) {
    const el = $('#toast');

    el.textContent = message;
    el.classList.add('show');

    setTimeout(
        () => el.classList.remove('show'),
        3600
    );
}

document.addEventListener('click', event => {
    const fav = event.target.closest('[data-favorite]');

    if (fav) {
        event.stopPropagation();
        toggleFavorite(fav.dataset.favorite);
        return;
    }

    const track = event.target.closest('.track');

    if (track) {
        playId(track.dataset.id);
    }
});

$('#playBtn').onclick = () => player.toggle();
$('#heroPlay').onclick = () => player.toggle();
$('#previousBtn').onclick = () => player.previous();
$('#nextBtn').onclick = () => player.next(true);
$('#nowFavorite').onclick = () => toggleFavorite(player.song.id);
$('#playerFavorite').onclick = () => toggleFavorite(player.song.id);

$('#shuffleBtn').onclick = () => {
    player.shuffle = !player.shuffle;

    updateUI();

    toast(
        `Shuffle ${player.shuffle ? 'on' : 'off'}`
    );
};

$('#repeatBtn').onclick = () => {
    player.repeat =
        player.repeat === 'off'
            ? 'all'
            : player.repeat === 'all'
                ? 'one'
                : 'off';

    updateUI();

    toast(`Repeat ${player.repeat}`);
};

$('#progressBar').oninput = e => {
    if (player.audio.duration) {
        player.audio.currentTime =
            player.audio.duration * e.target.value / 100;
    }
};

$('#volumeBar').value = player.audio.volume;

$('#volumeBar').oninput = e => {
    player.audio.volume = e.target.value;
    player.audio.muted = false;

    Store.saveSetting(
        'Volume',
        player.audio.volume
    );
};

$('#muteBtn').onclick = () => {
    player.audio.muted = !player.audio.muted;

    $('#muteBtn').textContent =
        player.audio.muted ? '◌' : '◖';

    $('#muteBtn').setAttribute(
        'aria-label',
        player.audio.muted ? 'Unmute' : 'Mute'
    );
};

$('#searchInput').oninput = e => {
    renderTracks(e.target.value);

    $('#clearSearch').classList.toggle(
        'hidden',
        !e.target.value
    );
};

$('#clearSearch').onclick = () => {
    $('#searchInput').value = '';
    $('#searchInput').focus();

    renderTracks();

    $('#clearSearch').classList.add('hidden');
};

document.querySelectorAll('.nav-link').forEach(link => {
    link.onclick = () => {
        currentView = link.dataset.view;

        document.querySelectorAll('.nav-link').forEach(x =>
            x.classList.toggle(
                'active',
                x === link
            )
        );

        ['favorites', 'recent'].forEach(id =>
            $('#' + id).classList.toggle(
                'hidden-section',
                currentView !== id
            )
        );

        if (currentView === 'songs') {
            window.scrollTo({
                top: $('#songs').offsetTop - 24,
                behavior: 'smooth'
            });
        }
    };
});

$('#voiceSearch').onclick = () => {
    const SpeechRecognition =
        window.SpeechRecognition ||
        window.webkitSpeechRecognition;

    if (!SpeechRecognition) {
        toast(
            'Voice search is not supported in this browser.'
        );
        return;
    }

    const recognition = new SpeechRecognition();

    recognition.lang = 'en-US';
    recognition.interimResults = false;

    $('#voiceSearch').classList.add('listening');

    recognition.start();

    recognition.onresult = e => {
        $('#searchInput').value =
            e.results[0][0].transcript;

        $('#searchInput').dispatchEvent(
            new Event('input')
        );
    };

    recognition.onerror = () =>
        toast(
            'Voice search could not start. Check microphone permission.'
        );

    recognition.onend = () =>
        $('#voiceSearch').classList.remove(
            'listening'
        );
};

const visits =
    Number(Store.setting('Visits', 0)) + 1;

Store.saveSetting('Visits', visits);

$('#visitCount').textContent =
    `Local visits: ${visits}`;

renderAll();
updateUI();