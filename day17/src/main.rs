use std::collections::{HashMap, HashSet};
use std::ops;

#[derive(PartialEq, Hash, Eq, Clone)]
struct Coordinate {
    x: i32,
    y: i32,
}

#[derive(PartialEq, Hash, Eq, Clone)]
struct Node {
    coord: Coordinate,
    left_straigh: u16,
    came_from: Coordinate,
}

impl Coordinate {
    fn reverse(&self) -> Self {
        Coordinate {
            x: -self.x,
            y: -self.y,
        }
    }
}
impl ops::Add<&Coordinate> for &Coordinate {
    type Output = Coordinate;

    fn add(self, rhs: &Coordinate) -> Self::Output {
        Coordinate {
            x: self.x + rhs.x,
            y: self.y + rhs.y,
        }
    }
}

impl Node {
    fn visit_neighbours(&self, field: Vec<&str>, hm: &mut HashSet<Node>) {
        for y_off in -1..=1 {
            for x_off in -1..=1 {
                if x_off * y_off != 0 {
                    //break;
                }
                let c = Coordinate { x: x_off, y: y_off };
                if c == self.came_from {
                    //break;
                }
                let n = Node {
                    coord: &self.coord + &c,
                    left_straigh: {
                        if c.reverse() == self.came_from {
                            self.left_straigh + 1
                        } else {
                            3
                        }
                    },
                    came_from: c,
                };
                if n.left_straigh > 3 {
                    continue;
                }
                if hm.contains(&n) {
                    continue;
                }
                hm.insert(n.clone());
            }
        }
    }
}

fn main() {
    let s = include_str!("../input");
    println!("{}", s);
    let _seen = HashSet::<Node>::new();
    let _field = s.lines().collect::<Vec<_>>();
}
