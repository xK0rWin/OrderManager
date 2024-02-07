import { Component, OnInit } from '@angular/core';
import { Statistics } from '../models/statistics.model';
import { HttpClient } from '@angular/common/http';
import { HOST } from '../config';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {
  statistics: Statistics | undefined;
  selectedDate: Date = new Date();

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    
  }

  getStatsOfDay(): void {
    console.log("getting stats...")
    this.http.get<Statistics>(HOST + "/statistics?date=" + this.selectedDate).subscribe({
      next: (statistics) => {
        console.log(statistics)
        this.statistics = statistics;
      }
    });
  }
}
