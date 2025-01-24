import { Injectable, Renderer2, RendererFactory2 } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DarkModeService {
  private isDarkMode = false;
  private renderer: Renderer2;

  constructor(rendererFactory: RendererFactory2) {
    this.renderer = rendererFactory.createRenderer(null, null);
    this.loadDarkModePreference();
  }

  toggleDarkMode(isDark: boolean): void {
    this.isDarkMode = isDark;
    if (this.isDarkMode) {
      this.renderer.addClass(document.documentElement, 'dark');
    } else {
      this.renderer.removeClass(document.documentElement, 'dark');
    }
    this.saveDarkModePreference();
  }

  getDarkModeStatus(): boolean {
    return this.isDarkMode;
  }
  private saveDarkModePreference(): void {
    localStorage.setItem('darkMode', this.isDarkMode ? 'true' : 'false');
  }

  private loadDarkModePreference(): void {
    const savedPreference = localStorage.getItem('darkMode');
    if (savedPreference === 'true') {
      this.isDarkMode = true;
      this.renderer.addClass(document.documentElement, 'dark');
    } else {
      this.isDarkMode = false;
      this.renderer.removeClass(document.documentElement, 'dark');
    }
  }


}
