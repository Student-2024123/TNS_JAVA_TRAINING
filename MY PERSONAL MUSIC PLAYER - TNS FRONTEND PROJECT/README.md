# Sonix Music Player

A responsive, frontend-only personal music player built with HTML, CSS, and vanilla JavaScript. It keeps favorites, listening history, volume, and local visit count in the browser's localStorage.

## Features

- 10-song library, search by title, artist, or album, and optional voice search
- HTML5 Audio playback with play/pause, previous/next, seek, volume and mute
- Shuffle and repeat off/all/one modes
- Persistent favorites and recently played tracks
- Responsive desktop and mobile layout, keyboard-friendly labeled controls, and error feedback

## Run locally

Open `index.html` in a modern browser, or serve this folder from any static file host. No build step, server, backend, or dependencies are needed.

## Add Music Files

The project uses local royalty-free MP3 files. No MP3 files are included in this repository, so the code is ready but playback cannot begin until the following files are downloaded manually and placed in `assets/audio/`.

Download the corresponding tracks from Mixkit Free Stock Music, verify the license that applies to your intended use and redistribution, then rename the downloaded files exactly as follows:

```text
hip-hop-02.mp3                 — Hip Hop 02 — Lily J
sun-and-his-daughter.mp3       — Sun and His Daughter — Eugenio Mininni
hazy-after-hours.mp3           — Hazy After Hours — Alejandro Magaña (A. M.)
tech-house-vibes.mp3           — Tech House vibes — Alejandro Magaña (A. M.)
driving-ambition.mp3           — Driving Ambition — Ahjay Stelino
deep-urban.mp3                 — Deep Urban — Eugenio Mininni
serene-view.mp3                — Serene View — Arulo
valley-sunset.mp3              — Valley Sunset — Alejandro Magaña (A. M.)
one-more-dance.mp3             — One More Dance — Arulo
just-keep-walking.mp3          — Just Keep Walking — Michael Ramir C.
```

The filenames must match `songs.js`. Once all files are present, Play loads `assets/audio/hip-hop-02.mp3`; selection, next/previous, seek, volume, shuffle, repeat, favorites, and recently played use the same HTML5 Audio player. The existing local SVG cover art in `assets/images/` is reused for every track.

## Project structure

```text
index.html
style.css          # styles
songs.js           # catalogue
storage.js         # safe localStorage helpers
player.js          # HTML5 Audio control logic
app.js             # rendering and interactions
assets/images/     # local cover artwork
assets/audio/      # add your own music files here
```

## GitHub Pages

Push this folder to a GitHub repository. In **Settings → Pages**, choose **Deploy from a branch**, select the branch and `/ (root)`, then save. The app is static and needs no additional configuration.

## Browser compatibility

Playback uses the standard HTML5 Audio API. Voice search uses the Web Speech API and is supported only by browsers that expose `SpeechRecognition` (or `webkitSpeechRecognition`); unsupported browsers show a helpful message.
