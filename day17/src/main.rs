use std::collections::{BinaryHeap, HashMap, HashSet, VecDeque};
use std::ops;

impl Node {
    fn get_neighbours(&self, start: Node, field: Vec<Vec<u32>>) -> Vec<Node> {
        let mut ret = Vec::<Node>::new();
        for y_off in -1..=1 {
            for x_off in -1..=1 {
                if x_off * y_off != 0 {
                    break;
                }
                let c = Coordinate { x: x_off, y: y_off };
                let n = Node {
                    coord: &self.coord + &c,
                    left_straigh: {
                        if c.reverse() == self.came_from {
                            self.left_straigh + 1
                        } else {
                            3
                        }
                    },
                    came_from: c.reverse(),
                    cost: field[c.y as usize][c.x as usize],
                };
                if n.left_straigh > 3 {
                    continue;
                }
                if n.came_from.reverse() == self.came_from {
                    continue;
                }
                ret.push(n);
            }
        }
        ret
    }
}
fn dijkstra(start: Node, field: Vec<&str>, hm: &mut HashSet<Node>) {
    let mut to_visit = VecDeque::<Visit>::new();
    let mut visited = HashSet::<Node>::new();
    let mut costs = HashMap::<Node, u32>::new();
    visited.insert(start.clone());
    to_visit.push_back(Visit { n: &start, cost: 0 });
    while to_visit.len() != 0 {
        let v = to_visit.pop_front().unwrap();
        let nb = v.n.get_neighbours();
        for n in nb {
            if !visited.insert(n.clone()) {
                break;
            }
            let new_distance = n.cost + v.n.cost;
            if let Some(old_distance) = costs.get(&n) {}
        }
    }
}
fn main() {
    let s = include_str!("../input");
    println!("{}", s);
    let _seen = HashSet::<Node>::new();
    let _field = s
        .lines()
        .map(|s| {
            s.chars()
                .map(|c| c.to_digit(10).unwrap())
                .collect::<Vec<u32>>()
        })
        .collect::<Vec<_>>();
    println!("{:?}", _field);
    dijkstra(
        Node {
            coord: (0, 0),
            left_straigh: 0,
            cost: 0,
            came_from: (),
        },
        field,
        hm,
    )
}

#[derive(PartialEq, Hash, Eq, Clone)]
struct Coordinate {
    x: i32,
    y: i32,
}

#[derive(PartialEq, Hash, Eq, Clone)]
struct Node {
    coord: Coordinate,
    left_straigh: u16,
    cost: u32,
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

struct Visit<'a> {
    n: &'a Node,
    cost: u32,
}
