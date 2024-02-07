import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PrintStateService {
  private stateMap: { [key: string]: boolean } = {};

  constructor() {
    this.loadStateMap();
  }

  setState(key: string, value: boolean): void {
    this.stateMap[key] = value;
    this.saveStateMap();
  }

  getState(key: string): boolean {
    return this.stateMap[key] ?? false;
  }

  private loadStateMap(): void {
    const stateJson = localStorage.getItem('printStateMap');
    if (stateJson) {
      this.stateMap = JSON.parse(stateJson);
    }
  }

  private saveStateMap(): void {
    localStorage.setItem('printStateMap', JSON.stringify(this.stateMap));
  }
}
