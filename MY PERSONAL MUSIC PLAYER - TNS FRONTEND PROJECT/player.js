class MusicPlayer {
  constructor(onChange) { this.audio = new Audio(); this.audio.preload = 'metadata'; this.index = 0; this.shuffle = false; this.repeat = 'off'; this.onChange = onChange; this.audio.volume = Store.setting('Volume', .75); this.bind(); }
  bind() {
    ['timeupdate', 'loadedmetadata', 'play', 'pause'].forEach(event => this.audio.addEventListener(event, () => this.onChange(event)));
    this.audio.addEventListener('error', () => {
      const mediaError = this.audio.error;
      console.error('Unable to load audio:', this.audio.src, mediaError ? `MediaError ${mediaError.code}: ${mediaError.message || 'unknown error'}` : 'unknown error');
      this.onChange('error');
    });
    this.audio.addEventListener('ended', () => this.repeat === 'one' ? (this.audio.currentTime = 0, this.play()) : this.next(this.repeat !== 'off'));
  }
  get song() { return songs[this.index]; }
  load(id, autoplay = true) {
    const index = songs.findIndex(song => song.id === id);
    if (index < 0) { console.error('Unable to load unknown song id:', id); return; }
    this.index = index;
    this.audio.src = this.song.audio;
    this.audio.load();
    Store.addRecent(id);
    this.onChange('track');
    if (autoplay) this.play();
  }
  play() {
    if (!this.audio.currentSrc) this.load(this.song.id, false);
    this.audio.play().catch(error => {
      console.error('Audio playback failed:', this.audio.src, error);
      this.onChange('error');
    });
  }
  toggle() { this.audio.paused ? this.play() : this.audio.pause(); }
  next(force = false) { if (this.repeat === 'off' && this.index === songs.length - 1 && !this.shuffle && !force) { this.audio.pause(); this.audio.currentTime = 0; return; } const next = this.shuffle ? this.randomIndex() : (this.index + 1) % songs.length; this.load(songs[next].id); }
  previous() { if (this.audio.currentTime > 3) { this.audio.currentTime = 0; return; } const previous = this.shuffle ? this.randomIndex() : (this.index - 1 + songs.length) % songs.length; this.load(songs[previous].id); }
  randomIndex() { if (songs.length < 2) return this.index; let n; do { n = Math.floor(Math.random() * songs.length); } while (n === this.index); return n; }
}
