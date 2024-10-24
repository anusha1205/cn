# SHORTEST PATH
graph = {
    1: [(2, 4), (3, 1)],
    2: [(1, 4), (3, 2), (4, 5)],
    3: [(1, 1), (2, 2), (4, 8), (5, 3)],
    4: [(2, 5), (3, 8), (5, 2)],
    5: [(3, 3), (4, 2)]
}

def dijkstra(graph, start):
    distances = {node: float('inf') for node in graph}
    distances[start] = 0
    previous_nodes = {node: None for node in graph}
    nodes = list(graph.keys())

    while nodes:
        min_node = None
        for node in nodes:
            if min_node is None:
                min_node = node
            elif distances[node] < distances[min_node]:
                min_node = node

        nodes.remove(min_node)

        for neighbor, weight in graph[min_node]:
            new_distance = distances[min_node] + weight
            if new_distance < distances[neighbor]:
                distances[neighbor] = new_distance
                previous_nodes[neighbor] = min_node

    return distances, previous_nodes

def shortest_path(previous_nodes, start, destination):
    path = []
    current_node = destination
    while current_node is not None:
        path.append(current_node)
        current_node = previous_nodes[current_node]
    path.reverse()
    return path

source = int(input("Enter the source node: "))
destination = int(input("Enter the destination node: "))
shortest_distances, previous_nodes = dijkstra(graph, source)

if destination in shortest_distances:
    print(f"The shortest path from node {source} to node {destination} is: {shortest_distances[destination]}")
    path = shortest_path(previous_nodes, source, destination)
    print(f"The path is: {' -> '.join(map(str, path))}")
else:
    print(f"Node {destination} is not reachable from node {source}.")
